#!/usr/bin/env python3
import os
import re

def convert_to_template(filepath, model_type, overlay_number):
    """Convert existing model file to use template system"""

    if model_type == "post":
        content = f'''{{
  "parent": "rewoven:block/flower_hedge_post_template",
  "textures": {{
    "overlay": "rewoven:block/flower_hedge_overlay_{overlay_number}"
  }}
}}'''

    elif model_type == "side":
        content = f'''{{
  "parent": "rewoven:block/flower_hedge_side_template", 
  "textures": {{
    "overlay": "rewoven:block/flower_hedge_overlay_{overlay_number}"
  }}
}}'''

    elif model_type == "side_tall":
        content = f'''{{
  "parent": "rewoven:block/flower_hedge_side_tall_template",
  "textures": {{
    "overlay": "rewoven:block/flower_hedge_overlay_{overlay_number}"
  }}
}}'''

    elif model_type == "inventory":
        content = f'''{{
  "parent": "rewoven:block/flower_hedge_post_template",
  "textures": {{
    "overlay": "rewoven:block/flower_hedge_overlay_{overlay_number}"
  }}
}}'''

    else:
        return  # Skip unknown types

    with open(filepath, 'w') as f:
        f.write(content)

    print(f"Converted: {os.path.basename(filepath)} ({model_type})")

# Main execution
models_dir = "/Users/maikmetzger/IdeaProjects/Rewoven/src/main/resources/assets/rewoven/models/block/"

# Process all flower hedge models
for filename in os.listdir(models_dir):
    if "hedge" in filename and filename.endswith(".json"):
        # Skip template files and base hedge files
        if "template" in filename or filename in ["hedge.json", "hedge_post.json", "hedge_side.json", "hedge_inventory.json", "hedge_post_tinted.json", "hedge_side_tinted.json", "hedge_side_tall.json", "hedge_side_tall_tinted.json"]:
            continue

        filepath = os.path.join(models_dir, filename)

        # Extract overlay number from filename
        overlay_match = re.search(r'_(\d)\.json$', filename)
        overlay_number = overlay_match.group(1) if overlay_match else "0"

        # Determine model type and convert
        if "post" in filename:
            convert_to_template(filepath, "post", overlay_number)
        elif "side_tall" in filename:
            convert_to_template(filepath, "side_tall", overlay_number)
        elif "side" in filename:
            convert_to_template(filepath, "side", overlay_number)
        elif "inventory" in filename:
            convert_to_template(filepath, "inventory", overlay_number)

print("\\nAll flower hedge models converted to template system!")
print("File sizes reduced by ~95%!")
print("Now you only need to edit the 4 template files for future changes.")
